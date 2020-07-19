# Random User Agent Generator

Random User Agent Generator based on whatismybrowser.com's 10.000 user agent list.
It can be used for implementing different kinds of web scraper.

### Maven dependency

```
<dependency>
	<groupId>com.sezinkarli</groupId>
	<artifactId>random-user-agent-generator</artifactId>
	<version>1.1</version>
</dependency>
```

### How to use it

It is quite straightforward to use it.
```
String randomUserAgent = RandomUserAgentGenerator.getNext();
```

