package com.malik.urfan.microservice;

import com.malik.urfan.microservice.domain.Crime;

import com.malik.urfan.microservice.utils.InvalidParametersException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceTest {

    Service service = new Service();

    @Test
    public void validatePostcode_validPostcode_true()
    {
        String postcode = "e14ns"; // On or near Westfield Way, Mile End
        String date = "2021-02";

        Crime[] crimeByLocation = service.getCrimeByLocation( postcode, date );

        assertTrue( crimeByLocation.length > 1 );
    }

    @Test (expected = InvalidParametersException.class)
    public void validatePostcode_validPostcode_false()
    {
        String postcode = "e1ans";
        String date = "2021-02";

        service.getCrimeByLocation( postcode, date );
    }

}