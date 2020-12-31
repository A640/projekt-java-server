package foodwarehouse.core.data.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeePersonalData(
        @JsonProperty("employeeId")     int employeeId,
        @JsonProperty("name")           String name,
        @JsonProperty("surname")        String surname,
        @JsonProperty("position")       String position,
        @JsonProperty("salary")         Float salary) {
}
