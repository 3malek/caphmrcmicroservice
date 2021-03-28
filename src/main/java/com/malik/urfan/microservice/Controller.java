package com.malik.urfan.microservice;

import com.malik.urfan.microservice.domain.Crime;
import com.malik.urfan.microservice.domain.CrimeCategoryProducer;
import com.malik.urfan.microservice.utils.NoDataFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Service service = new Service();

    @GetMapping("/crime/categories")
    public CrimeCategoryProducer crimeCategories()
    {
        CrimeCategoryProducer crimeCategories = service.getCrimeCategories();

        if ( crimeCategories.getCategories().length < 1 )
        {
            throw new NoDataFoundException();
        }
        return crimeCategories;
    }


    @GetMapping("/crimes")
    public Crime[] crimeByLocation( @RequestParam(value = "postcode") String postcode,
                                    @RequestParam(value = "date") String yearMonth )
    {
        Crime[] crimeByLocation = service.getCrimeByLocation( postcode, yearMonth );

        if ( crimeByLocation.length < 1 )
        {
            throw new NoDataFoundException();
        }
        return crimeByLocation;
    }

}
