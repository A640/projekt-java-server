package foodwarehouse.web.response.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import foodwarehouse.core.data.storage.Storage;

public record StorageEmployeeResponse(
        @JsonProperty(value = "storage", required = true)       StorageDataResponse storageDataResponse,
        @JsonProperty(value = "address", required = true)       foodwarehouse.web.response.address.AddressResponse AddressResponse,
        @JsonProperty(value = "quantity", required = true)      int quantity) {

    public static StorageEmployeeResponse fromStorage(Storage storage, int quantity) {
        return new StorageEmployeeResponse(
                StorageDataResponse.fromStorage(storage),
                foodwarehouse.web.response.address.AddressResponse.fromAddress(storage.address()),
                quantity);
    }
}
