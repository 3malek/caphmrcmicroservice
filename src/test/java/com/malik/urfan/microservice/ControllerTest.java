package com.malik.urfan.microservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;

import org.springframework.http.HttpStatus;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ControllerTest {

    private StringBuilder yearMonth;


    @Test
    public void getCrimeCategories_success() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        URI uri = new URI( "http://localhost:8080/crime/categories" );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.OK.value(), statusCode );
    }


    @Test
    public void crimeByLocation_invalidPostcode_statusCode400BadRequest() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        // No data for current month.  Need to go back one month
        yearMonth = createDate( 0, -1 );
        String date = yearMonth.toString();
        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.OK.value(), statusCode );
    }

    @Test
    public void crimeByLocation_invalidDateTooFarBack_statusCode400BadRequest() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get( Calendar.YEAR );
        int yearsSince2017 = currentYear - 2017;

        yearMonth = createDate( ( yearsSince2017 * -1) , 0 );
        String date = yearMonth.toString();

        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.BAD_REQUEST.value(), statusCode );
    }

    @Test
    public void crimeByLocation_invalidDateFutureMonth_statusCode400BadRequest() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        yearMonth = createDate( 0, 1 );
        String date = yearMonth.toString();
        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.BAD_REQUEST.value(), statusCode );
    }

    @Test
    public void crimeByLocation_invalidDateFutureYear_statusCode400BadRequest() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        yearMonth = createDate( 1, 0 );
        String date = yearMonth.toString();
        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.BAD_REQUEST.value(), statusCode );
    }

    @Test
    public void crimeByLocation_invalidDate_statusCode400BadRequest() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        Calendar calendar = Calendar.getInstance();
        // constructing wrong format, i.e. MM-YYYY
        yearMonth = new StringBuilder( createMonth( calendar, 0 ) );
        yearMonth.append("-");
        yearMonth.append( createYear( calendar, 0 ) );
        String date = yearMonth.toString();

        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.BAD_REQUEST.value(), statusCode );
    }

    @Test
    public void crimeByLocation_currentMonth_statusCode404() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "so171bj"; // No data exists for this location

        // Set date to current month (when no data should exist)
        yearMonth = createDate( 0, -1 );
        String date = yearMonth.toString();
        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.NOT_FOUND.value(), statusCode );
    }


    @Test
    public void crimeByLocation_httpResponse_success() throws
            IOException,
            URISyntaxException,
            InterruptedException
    {
        // Given
        String postcode = "e14ns"; // On or near Westfield Way, Mile End

        // No data for current month.  Need to go back one month
        yearMonth = createDate( 0, -1 );
        String date = yearMonth.toString();
        URI uri = new URI( "http://localhost:8080/crimes?postcode="+postcode +"&date="+date );

        // When
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse response = client.send( request, HttpResponse.BodyHandlers.ofString() );
        int statusCode = response.statusCode();

        // Then
        assertEquals( HttpStatus.OK.value(), statusCode );
    }


    private StringBuilder createDate( int yearsInFuture, int monthsInFuture )
    {
        Calendar calendar = Calendar.getInstance();
        yearMonth = new StringBuilder( createYear( calendar, yearsInFuture ) );
        yearMonth.append("-");
        String month = createMonth( calendar, monthsInFuture );
        yearMonth.append( month );
        return yearMonth;
    }

    private String createYear( Calendar calendar, int yearsInFuture )
    {
        return Integer.toString( calendar.get( Calendar.YEAR ) + yearsInFuture );
    }

    private String createMonth( Calendar calendar, int monthsInFuture )
    {
        //  Calendar.MONTH returns current month minus one.  We add one for current month
        String month = Integer.toString( calendar.get( Calendar.MONTH ) + 1 + monthsInFuture );

        if ( month.length() < 2 )
        {
            month = "0" + month;
        }
        return month;
    }
}
