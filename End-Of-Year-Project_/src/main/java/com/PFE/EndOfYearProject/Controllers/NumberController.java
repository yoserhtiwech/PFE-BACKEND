package com.PFE.EndOfYearProject.Controllers;

import com.PFE.EndOfYearProject.RequestResponse.NumberRequest;
import com.PFE.EndOfYearProject.RequestResponse.NumberResponse;
import com.PFE.EndOfYearProject.Services.NumberService;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Numbers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Number")
@RequiredArgsConstructor
public class NumberController {
    private final NumberService numberService;
    @GetMapping("/numberList")
    public ResponseEntity<List<NumberResponse>> getAllNumbers() {
        List<NumberResponse> numbers = numberService.getAllNumbers();
        return ResponseEntity.ok(numbers);
    }
    @GetMapping("/numberavailble")
    public ResponseEntity<List<NumberResponse>> getAvailableNumbers() {
        List<NumberResponse> numbers = numberService.getAvailableNumbers();
        return ResponseEntity.ok(numbers);
    }
    @PostMapping ("/addNumber")
    public ResponseEntity<Integer> SaveNumber(@Valid @RequestBody NumberRequest numberRequest){
        return ResponseEntity.ok(numberService.saveNumber(numberRequest));
    }


}
