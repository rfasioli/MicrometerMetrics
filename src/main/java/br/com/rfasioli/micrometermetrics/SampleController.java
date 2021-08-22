package br.com.rfasioli.micrometermetrics;

import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController("/sample")
@Timed("sample")
@Log4j2
public class SampleController {

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  @GetMapping
  @Timed(value = "sample.all", longTask = true)
  public List<String> listSample() throws InterruptedException {
    int seconds2Sleep = SECURE_RANDOM.nextInt(500);
    log.debug("waiting for {}", seconds2Sleep);
    TimeUnit.MILLISECONDS.sleep(seconds2Sleep);
    return Arrays.asList("Jim", "Tom", "Tim");
  }

  @PostMapping
  @Timed(value = "sample.update", longTask = true)
  public List<String> putSample() throws InterruptedException {
    int seconds2Sleep = SECURE_RANDOM.nextInt(1000);
    log.debug("waiting for {}", seconds2Sleep);
    TimeUnit.MILLISECONDS.sleep(seconds2Sleep);
    return Arrays.asList("Jim", "Tom", "Tim");
  }

  @GetMapping("/asset")
  @Timed(value = "sample.asset", longTask = true)
  public void test() throws Exception {
    throw new Exception("error!");
  }

  @GetMapping("/property")
  @Timed(value = "sample.property", longTask = true)
  public void property(HttpServletResponse response) throws IOException {
    response.sendRedirect("/asset");
  }


}
