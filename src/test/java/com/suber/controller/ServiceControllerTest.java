package com.suber.controller;

import com.suber.data.Service;
import com.suber.data.Service;
import com.suber.dto.ServiceListDTO;
import com.suber.dto.ServiceDTO;
import com.suber.repository.ServiceRepository;
import com.suber.repository.ServiceRepository;
import com.suber.util.TestData;
import com.suber.util.mapper.DataMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerTest {


    @Autowired
    ServiceController controller;

    @Autowired
    ServiceRepository repository;

    @Test
    void serviceShouldBeDeletedAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));

        ResponseEntity<HttpStatus> status = controller.deleteService(savedService.getId());

        Optional<Service> found = repository.findById(savedService.getId());

        Assert.assertFalse(found.isPresent());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void allServicesShouldBeDeletedAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        Service savedService2 = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        List<Service> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<HttpStatus> status = controller.deleteAllServices();

        List<Service> foundDeleted = repository.findAll();
        System.out.println("size: " + foundDeleted.size());

        Assert.assertTrue(found.size() > 0);
        Assert.assertEquals(0, foundDeleted.size());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void serviceShouldBeFoundWithNameAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));

        ResponseEntity<ServiceListDTO> servicesWithThatName = controller.findByName("Lumitöitä");
        ServiceListDTO list = servicesWithThatName.getBody();

        Assert.assertTrue(list.getServices().size() > 0);
        Assert.assertTrue(list.getServices().get(0).getName().equals("Lumitöitä"));
        Assert.assertEquals(HttpStatus.OK, servicesWithThatName.getStatusCode());
    }

    @Test
    void serviceShouldNotBeFoundWithFirstnameAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));

        ResponseEntity<ServiceListDTO> servicesWithThatName = controller.findByName("Jaakko");
        ServiceListDTO list = servicesWithThatName.getBody();

        Assert.assertTrue(list == null);
        Assert.assertEquals(HttpStatus.NO_CONTENT, servicesWithThatName.getStatusCode());
    }

    @Test
    void serviceShouldBeFoundWithNeAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));

        ResponseEntity<ServiceListDTO> servicesWithThatName = controller.findByName("Lumitöitä");
        ServiceListDTO list = servicesWithThatName.getBody();

        Assert.assertTrue(list.getServices().size() > 0);
        Assert.assertTrue(list.getServices().get(0).getName().equals("Lumitöitä"));
        Assert.assertEquals(HttpStatus.OK, servicesWithThatName.getStatusCode());
    }

    @Test
    void serviceWithAddressShouldBeUpdatedAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        savedService.setName("Väliaho");

        ServiceDTO serviceDTOFromService = DataMapper.getInstance().convertToDto(savedService);
        ResponseEntity<ServiceDTO> updatedService =
                controller.updateService(savedService.getId(), serviceDTOFromService);

        Assert.assertEquals(HttpStatus.OK, updatedService.getStatusCode());
        Assert.assertTrue(updatedService.getBody().getName().equals("Väliaho"));
    }

    @Test
    void serviceShouldBeUpdatedAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        savedService.setName("Väliaho");

        ServiceDTO serviceDTOFromService = DataMapper.getInstance().convertToDto(savedService);
        ResponseEntity<ServiceDTO> updatedService =
                controller.updateService(savedService.getId(), serviceDTOFromService);

        Assert.assertEquals(HttpStatus.OK, updatedService.getStatusCode());
        Assert.assertTrue(updatedService.getBody().getName().equals("Väliaho"));
    }

    @Test
    void serviceShouldNotBeUpdatedAndReturnFailed() {

        long wrongId = 10000;

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        savedService.setName("Väliaho");

        ServiceDTO serviceDTOFromService = DataMapper.getInstance().convertToDto(savedService);
        ResponseEntity<ServiceDTO> updatedService =
                controller.updateService(wrongId, serviceDTOFromService);

        Assert.assertEquals(HttpStatus.NOT_FOUND, updatedService.getStatusCode());
        Assert.assertTrue(updatedService.getBody() == null);
    }

    @Test
    void getAllServiceAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        Service savedService2 = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));
        List<Service> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<ServiceListDTO> listOfServices = controller.getAllServices();
        System.out.println("size: " + listOfServices.getBody().getServices().size());

        Assert.assertEquals(HttpStatus.OK, listOfServices.getStatusCode());
        Assert.assertTrue(listOfServices.getBody().getServices().size() > 0);

    }

    @Test
    void createServiceAndReturnedSuccess() {

        ResponseEntity<ServiceDTO> createdService = controller.createService(TestData.getServiceDTO());
        System.out.println(createdService);
        Optional<Service> savedService = repository.findById(createdService.getBody().getId());


        Assert.assertTrue(savedService.isPresent());
        Assert.assertEquals(HttpStatus.CREATED, createdService.getStatusCode());

    }

    @Test
    void getServiceByIdAndReturnedSuccess() {

        Service savedService = repository.save(DataMapper.getInstance().convertToEntity(TestData.getServiceDTO()));

        ResponseEntity<ServiceDTO> foundService = controller.getServiceById(savedService.getId());
        System.out.println(foundService);

        Assert.assertEquals(savedService.getId(), foundService.getBody().getId());
        Assert.assertEquals(HttpStatus.OK, foundService.getStatusCode());

    }

    @Test
    public void exceptionThrownFromGetServiceById() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void resourceNotFoundResponseSentFromGetServiceById() {
        // ResponseEntity<>(HttpStatus.NOT_FOUND)
        long missingId = 100000;
        ResponseEntity<ServiceDTO> foundService = controller.getServiceById(missingId);

        // TODO
        //CompanyDTO dto = null;
        //DataMapper.getInstance().convertToEntity(dto);

        System.out.println(foundService);

        Assert.assertEquals(HttpStatus.NOT_FOUND, foundService.getStatusCode());

    }


}
