package generator;

import exception.UserAgentLoadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomUserAgentGenerator {

  private static final String USER_AGENT_FILE = "user-agents.txt";
  private static final int NUMBER_OF_PREDEFINED_USER_AGENTS = 10_000;

  private static Random random;
  private static List<String> userAgents;

  private RandomUserAgentGenerator() {}

  static {
    random = new Random();
    userAgents = loadUserAgents();
  }

  private static List<String> loadUserAgents() {
    final InputStream inputStream = ClassLoader.getSystemResourceAsStream(USER_AGENT_FILE);

    if (inputStream == null) {
      throw new UserAgentLoadingException("User agents cannot be loaded from file. InputStream is null");
    }

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
      return bufferedReader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      throw new UserAgentLoadingException("User agents cannot be read by BufferedReader", e);
    }
  }

  public static String getNext() {
    if (userAgents == null) {
      throw new UserAgentLoadingException("User agents cannot be loaded from file");
    }
    return getRandomUserAgent();
  }

  private static String getRandomUserAgent() {
    return userAgents.get(random.nextInt(NUMBER_OF_PREDEFINED_USER_AGENTS));
  }
}
