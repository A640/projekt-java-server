package foodwarehouse.web.component;

import foodwarehouse.core.data.productBatch.ProductBatch;
import foodwarehouse.core.service.ConnectionService;
import foodwarehouse.core.service.ProductBatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class DiscountMonitor {

    private final ProductBatchService productBatchService;
    private final ConnectionService connectionService;

    public DiscountMonitor(ProductBatchService productBatchService, ConnectionService connectionService) {
        this.productBatchService = productBatchService;
        this.connectionService = connectionService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void checkDiscount() {
        //check if database is reachable
        if(!connectionService.isReachable()) {
            return;
        }

        System.out.println("Calculating discounts!");

        LocalDate now = LocalDate.now();
        LocalDate startDiscount = now.plusDays(3);
        LocalDate endDiscount = now.plusDays(15);

        List<ProductBatch> batches = productBatchService.findProductBatches();

        for(ProductBatch pb : batches) {
            LocalDate productEatByDate = pb.eatByDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(productEatByDate.isBefore(endDiscount) && productEatByDate.isAfter(startDiscount)) {
                long temp = productEatByDate.toEpochDay() - now.toEpochDay() - 4L;
                int discount = 20 + 5 * (10 - (int) temp);
                productBatchService.updateProductBatch(pb.batchId(), pb.product(), pb.batchNumber(), pb.eatByDate(), discount, pb.packagesQuantity());
            }
        }
    }
}
