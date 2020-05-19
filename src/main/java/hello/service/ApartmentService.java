package hello.service;

import hello.dao.ApartmentRepository;
import hello.dao.CustomerRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

public class ApartmentService {

    private ApartmentRepository apartmentRepository;
}


