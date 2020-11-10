package br.com.iteris.meetup.app4Jmeter.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.meetup.app4Jmeter.core.TestComponent;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/stressTest")
class RestForJmeterController {

	@Autowired
	private TestComponent testComponent;

	@PostMapping("/listType")
	@ApiOperation(value = "POST on list Teste Jmeter")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> postListTest(@RequestBody String[] values, HttpServletRequest httpServletRequest) {

		testComponent.addOnList(values);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/setType")
	@ApiOperation(value = "POST on set Teste Jmeter")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> postSetTest(@RequestBody String[] values, HttpServletRequest httpServletRequest) {
		testComponent.addOnSet(values);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/listType")
	@ApiOperation(value = "PUT on list Teste Jmeter")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> putTestList(@RequestBody String request, HttpServletRequest httpServletRequest) {

		String newValue = testComponent.updateItemAtList(request, UUID.randomUUID().toString());

		return ResponseEntity.ok().body(newValue);
	}

	@PutMapping("/setType")
	@ApiOperation(value = "PUT on set Teste Jmeter")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> putTestSet(@RequestBody String request, HttpServletRequest httpServletRequest) {
		String newValue = testComponent.updateItemAtSet(request, UUID.randomUUID().toString());

		return ResponseEntity.ok().body(newValue);
	}

	@DeleteMapping("/setType/remove/all")
	@ApiOperation(value = "DELETE Teste Jmeter -Set")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> removeTestSet(HttpServletRequest httpServletRequest) {

		testComponent.getItemsSet().clear();
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/listType/remove/all")
	@ApiOperation(value = "DELETE Teste Jmeter -List")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> removeTestList( HttpServletRequest httpServletRequest) {

		testComponent.getItemsList().clear();

		return ResponseEntity.accepted().build();
	}

	@GetMapping("/setType/get/all")
	@ApiOperation(value = "GET Teste Jmeter - Set")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> listTestSet( HttpServletRequest httpServletRequest) {

		return ResponseEntity.accepted().body(testComponent.getItemsSet().toString());
	}

	@GetMapping("/listType/get/all")
	@ApiOperation(value = "GET Teste Jmeter -List")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> listTestList(HttpServletRequest httpServletRequest) {

		return ResponseEntity.accepted().body(testComponent.getItemsList().toString());
	}

}
