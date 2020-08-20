package shop.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shop.Order;

@FeignClient(name="delivery", url="${api.url.delivery}")
public interface CancellationService {

    @RequestMapping(method = RequestMethod.POST, path="/cancellations")
    public void cancel(@RequestBody Cancellation cancellation);

}
