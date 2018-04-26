package com.sherl.tmall.web.propertyvalue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sherl.tmall.entity.PropertyValue;
import com.sherl.tmall.service.PropertyValueService;

@Controller
public class PropertyValueController {

	@Autowired
	private PropertyValueService valueService;

	@RequestMapping(value = "/admin/{cid}/products/{pid}/propertyvalue", method = RequestMethod.GET)
	public String index() {
		return "admin/propertyValue";
	}

	@RequestMapping(value = "/admin/{cid}/products/{pid}/propertyvalues", method = RequestMethod.GET)
	@ResponseBody
	public List<PropertyValue> list(@PathVariable int pid) {
		return valueService.listEdit(pid);
	}

	@RequestMapping(value = "/admin/{cid}/products/{pid}/propertyvalues/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestParam("value") String value, @PathVariable int pid, @PathVariable int id) {
		PropertyValue ppv = new PropertyValue();
		ppv.setId(id);
		ppv.setValue(value);

		valueService.update(ppv);
	}

}
