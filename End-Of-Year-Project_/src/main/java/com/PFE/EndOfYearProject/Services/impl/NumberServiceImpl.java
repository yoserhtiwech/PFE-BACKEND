package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Repository.NumberRepository;
import com.PFE.EndOfYearProject.Repository.UserRepository;
import com.PFE.EndOfYearProject.RequestResponse.NumberRequest;
import com.PFE.EndOfYearProject.RequestResponse.NumberResponse;
import com.PFE.EndOfYearProject.Services.NumberService;
import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberServiceImpl implements NumberService {
    @Autowired
    public NumberRepository numberRepository;
public UserRepository userRepository;
    @Override
    public Integer saveNumber(NumberRequest numberDto) {

       Numbers number= new Numbers();

       number.setNum(numberDto.getNum());
       number.setAddress("y");
        number.setType("y");
        number.setCountry("y");
        number.setRegion("y");
        return numberRepository.save(number).getId();

    }

    @Override
    public NumberDto findNumberById(long numberId) {
        return null;
    }

    @Override
    public List<NumberResponse> getAllNumbers() {
        return numberRepository.findAll().stream().map(userNumber -> {
            NumberResponse dto = new NumberResponse();
            dto.setId(userNumber.getId());
            dto.setNum(userNumber.getNum());
            if (userNumber.getUser() != null) {
                dto.setUsername(userNumber.getUser().getFullName());
            }
            return dto;
        }).collect(Collectors.toList());}
 @Override
  public List<NumberResponse> getAvailableNumbers() {
     return numberRepository.findAll().stream()
             .filter(userNumber -> userNumber.getUser() == null) // Filter out numbers with associated users
             .map(userNumber -> {
                 NumberResponse dto = new NumberResponse();
                 dto.setId(userNumber.getId());
                 dto.setNum(userNumber.getNum());
                 return dto;
             })
             .collect(Collectors.toList());}

    @Override
    public Numbers getUserNumber(UserDto user) {
        return numberRepository.findByuser_id(user.getId()).orElseThrow(() -> new RuntimeException("number not found"));
    }
}



