package pl.edu.amu.pracprog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import model.Employee;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class JacksonSerialization {

    final static Logger logger = Logger.getLogger(JacksonSerialization.class);

    public static void serializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        // Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        Employee employee = objectsCreator.getEmp();

        // Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), employee);
        String jsonString = mapper.writeValueAsString(employee);

        logger.info("Printing serialized original object " + fileSuffix);
        System.out.println(jsonString);

        // Deserialize from file
        Employee deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), Employee.class);

        // Give a rise
        deserializedEmployee.setSalary(deserializedEmployee.getSalary() * 2);

        // Serialize back
        mapper.writeValue(new File("result-modified." + fileSuffix), deserializedEmployee);
        String modifiedJsonString = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing serialized modified object " + fileSuffix);
        System.out.println(modifiedJsonString);
    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        // Deserialized employee object from employees.* file in resources
        InputStream employeeIs = JacksonSerialization.class.getClassLoader().
                getResourceAsStream("employee." + fileSuffix);

        // Read value - set class type of serialization
        Employee deserializedEmployee = mapper.readValue(employeeIs, Employee.class);

        // Give employee big salary
        deserializedEmployee.setSalary(deserializedEmployee.getSalary() * 3);

        String modifiedSerialized = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing modified re-serialized employee to " + fileSuffix);

        System.out.println(modifiedSerialized);
    }

    public static void zad4(ObjectMapper mapper) throws IOException {
        ModelObjectsCreator creator = new ModelObjectsCreator();
        Employee employee = creator.getEmp();
        List<Employee> employees = employee.getSubworkers();
        mapper.writeValue(new File("zad4.json"), employees);
        List importedEmployees = mapper.readValue(new File("zad4.json"), List.class);
        System.out.println(importedEmployees);
    }

    public static void zad5(XmlMapper mapper) throws IOException {
        ModelObjectsCreator creator = new ModelObjectsCreator();
        Employee employee = creator.getEmp();
        mapper.writeValue(new File("zad5.xml"), employee);
        Employee importedEmployee = mapper.readValue(new File("zad5.xml"), Employee.class);
        System.out.println(importedEmployee.getSalary());
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper jsonMapper = JsonMapper.builder().addModule(new JodaModule()).build();
        XmlMapper xmlMapper = new XmlMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        serializeDemo(jsonMapper, "json");
//        deserializeDemo(jsonMapper, "json");
        zad4(jsonMapper);
//        zad5(xmlMapper);
    }
}
