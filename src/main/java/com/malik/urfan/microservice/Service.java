package com.malik.urfan.microservice;

import com.malik.urfan.microservice.domain.*;
import com.malik.urfan.microservice.utils.InvalidParametersException;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

public class Service {

    private static String[] crimeCategories;
    private static CrimeCategoryProducer crimeCategoryProducer;

    public CrimeCategoryProducer getCrimeCategories() {
        return crimeCategoryProducer;
    }

    public void setCrimeCategories( CrimeCategoryConsumer[] crimeCategories )
    {
        Service.crimeCategories = new String[crimeCategories.length];

        for (int i=0; i<crimeCategories.length; i++)
        {
            Service.crimeCategories[i] = crimeCategories[i].getUrl();
        }
        crimeCategoryProducer = new CrimeCategoryProducer( Service.crimeCategories );
    }

    public Crime[] getCrimeByLocation( String postcode, String date )
    {
        return loadCrimeData( postcode, date);
    }

    private Crime[] loadCrimeData( String postcode, String date )
    {
        RestTemplate restTemplate = new RestTemplate();

        validatePostcode( postcode );

        GlobalCoordinatePosition globalCoordinatePosition = restTemplate.getForObject(
                "http://api.postcodes.io/postcodes/" + postcode, GlobalCoordinatePosition.class);

        Result locationResult = globalCoordinatePosition.getResult();
        String longitude = locationResult.getLongitude();
        String latitude = locationResult.getLatitude();

        String year = date.substring( 0, 4 );
        String month = date.substring( 5, 7 );
        validateDate( year, month );

        Crime[] crimeByLocation = restTemplate.getForObject(
        "https://data.police.uk/api/crimes-at-location?date=" + date + "&lat="+latitude + "&lng="+longitude,
                Crime[].class );

        Location location = new Location();
        location.setLatitude( latitude );
        location.setLongitude( longitude );
        location.setPostcode( postcode.toUpperCase() );

        for (int i=0; i<crimeByLocation.length; i++)
        {
            crimeByLocation[i].setLocation( location );
        }
        return crimeByLocation;
    }

    /* Postcode must be of format "SO171BJ" (7 chars) or "E14NS" (6 chars) */
    private void validatePostcode(String s )
    {
        int lengthOfString = s.length();
        if ( lengthOfString < 5  ||
                lengthOfString > 7  ||
                ! isOfEnglishAlphabet( s.charAt( 0 ) ) ||
                ! isOfEnglishAlphabet( s.charAt( lengthOfString-1 ) ) ||
                ! isOfEnglishAlphabet( s.charAt( lengthOfString-2 ) ) ||
                ! isDigit0To9( s.charAt( lengthOfString-3 ) ) )
        {
            throw new InvalidParametersException("Attempting to use a non-valid postcode " + s );
        }
    }

    private boolean isDigit0To9( char c )
    {
        if ( (c >= '1' && c <= '9' ) )
        {
            return true;
        }
        return false;
    }

    /* https://stackoverflow.com/questions/28053044/to-check-whether-a-character-is-of-english-alphabet-a-za-z/28053115 */
    private boolean isOfEnglishAlphabet( char c )
    {
        if ( (c >= 'a' && c <= 'z' ) || ( c >= 'A' && c <= 'Z') )
        {
            return true;
        }
        return false;
    }

    private void validateDate( String yearParameter, String monthParameter ) throws InvalidParametersException
    {
        if ( yearParameter.contains( "-" ) || monthParameter.contains( "-" ) )
        {
            throw new InvalidParametersException("Attempting to use a non-valid date " +
                    "month: " + monthParameter + " year: " + yearParameter );
        }
        int yearInt = Integer.parseInt( yearParameter );
        int monthInt = Integer.parseInt( monthParameter );

        int yearToday = Calendar.getInstance().get( Calendar.YEAR );
        // for current month, need to add one
        int monthToday = Calendar.getInstance().get( Calendar.MONTH ) + 1;

        // Valid months of year
        if ( monthInt > 12 || monthInt < 1 )
        {
            throw new InvalidParametersException("Attempting to use a non-valid date " +
                    "month: " + monthInt + " year: " + yearInt);
        }

        // Month not in future
        if ( ( monthInt > monthToday && yearInt==yearToday ) )
        {
            throw new InvalidParametersException("Attempting to use a non-valid date " +
                    "month: " + monthInt + " year: " + yearInt);
        }

        // Future years and before April 2018 (distant past)
        if ( yearInt > yearToday || ( yearInt < 2019 && monthInt < 4 ) )
        {
            throw new InvalidParametersException("Attempting to use a non-valid date " +
                    "month: " + monthInt + " year: " + yearInt);
        }
    }
}
