# Random User Agent Generator

Random User Agent Generator based on whatismybrowser.com's 10.000 user agent list.
It can be used for implementing different kinds of web scraper.

### Maven dependency

```
<dependency>
	<groupId>com.sezinkarli</groupId>
	<artifactId>random-user-agent-generator</artifactId>
	<version>1.2</version>
</dependency>
```

### How to use it

It is quite straightforward to use it.
For getting a random user agent (~30% chance of getting mobile so ~70% of getting non-mobile):
```
String randomUserAgent = RandomUserAgentGenerator.getNext();
```

For specifically getting random mobile user agent:  
```
String randomMobileUserAgent = RandomUserAgentGenerator.getNextMobile();
```

For specifically getting random non-mobile user agent:  
```
String randomNonMobileUserAgent = RandomUserAgentGenerator.getNextNonMobile();
```

