package generator;

import exception.UserAgentLoadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Random User Agent Generator class.
 * */
public class RandomUserAgentGenerator {

  private static final String USER_AGENT_MOBILE_FILE = "user-agents-mobile.txt";
  private static final String USER_AGENT_NON_MOBILE_FILE = "user-agents-non-mobile.txt";

  private static final Random random;
  private static final List<String> mobileUserAgents;
  private static final List<String> nonMobileUserAgents;

  private RandomUserAgentGenerator() {}

  static {
    random = new Random();
    mobileUserAgents = loadUserAgentByFileName(USER_AGENT_MOBILE_FILE);
    nonMobileUserAgents = loadUserAgentByFileName(USER_AGENT_NON_MOBILE_FILE);
  }

  private static List<String> loadUserAgentByFileName(String fileName) {
    final InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);

    if (inputStream == null) {
      throw new UserAgentLoadingException("User agents cannot be loaded from file. InputStream is null");
    }

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
      return bufferedReader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      throw new UserAgentLoadingException("User agents cannot be read by BufferedReader", e);
    }
  }

  /**
   * Static method to fetch a random user agent as String. It randomly chooses a user agent from a
   * list of 10.000.
   *
   * @return random user agent as String
   */
  public static String getNext() {
    if (mobileUserAgents == null || nonMobileUserAgents == null) {
      throw new UserAgentLoadingException("User agents cannot be loaded from file");
    }

    final int userAgentCount = getCombinedUserAgentCount();
    final int randomIndex = random.nextInt(userAgentCount);

    return getAgentBasedOnIndex(randomIndex);
  }

  private static int getCombinedUserAgentCount() {
    return mobileUserAgents.size() + nonMobileUserAgents.size();
  }

  private static String getAgentBasedOnIndex(int randomIndex) {
    if (isIndexInMobileInterval(randomIndex)) {
      return mobileUserAgents.get(randomIndex);
    }

    return nonMobileUserAgents.get(getIndexForNonMobile(randomIndex));
  }

  private static int getIndexForNonMobile(int randomIndex) {
    return randomIndex - mobileUserAgents.size();
  }

  private static boolean isIndexInMobileInterval(int randomIndex) {
    return randomIndex < mobileUserAgents.size();
  }

  /**
   * Static method to fetch a random mobile user agent as String. It randomly chooses a user agent
   * from a list of 2.894 .
   *
   * @return random user agent as String
   */
  public static String getNextMobile() {
    return getNextBasedOnAgentList(mobileUserAgents);
  }

  /**
   * Static method to fetch a random nonmobile user agent as String. It randomly chooses a user
   * agent from a list of 7.106 .
   *
   * @return random user agent as String
   */
  public static String getNextNonMobile() {
    return getNextBasedOnAgentList(nonMobileUserAgents);
  }

  private static String getNextBasedOnAgentList(List<String> userAgents) {
    if (userAgents == null) {
      throw new UserAgentLoadingException("User agents cannot be loaded from file");
    }

    final int randomIndex = random.nextInt(userAgents.size());

    return userAgents.get(randomIndex);
  }
}
