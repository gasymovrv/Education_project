package examples.multithreading.pool;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadPoolTest2 {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    var list = new ArrayList<CallableTask>();
    for (int i = 0; i < 5; i++) {
      list.add(new CallableTask(i));
    }
    var futures = executorService.invokeAll(list);
    System.out.println("isShutdown() = " + executorService.isShutdown());
    System.out.println("isTerminated() = " + executorService.isTerminated());
    System.out.println("All tasks completed: " + futures.stream().map((f)-> {
      try {
        return f.get();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList()));

    executorService.shutdown();
    System.out.println("After shutdown:");
    System.out.println("\tisShutdown() = " + executorService.isShutdown());
    System.out.println("\tisTerminated() = " + executorService.isTerminated());
  }
}

class CallableTask implements Callable<Integer> {

  private final Integer id;

  public CallableTask(int id) {
    this.id = id;
  }

  @Override
  public Integer call() throws Exception {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("-------- Task " + id + " was completed -------");
    return id;
  }
}
