package pl.pb.grpcexample.springbootintegrationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;

@Component
public class UpdaterComponent {

  @Autowired
  private TextService textService;

  @Scheduled(fixedRate = 5000)
  public void run() {
    GetStatisticResponse run = textService.run();
    System.out.println(run);
  }

}
