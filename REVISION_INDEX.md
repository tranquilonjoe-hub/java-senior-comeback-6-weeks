# Senior Java Developer Comeback — Revision Index



**Repository:** java-senior-comeback-6-weeks  

**Java:** JDK 21  

**Current milestone:** Day 7 — JUnit 5, Mockito and Testcontainers



## Week 1 — Core Java and Modern Java



| Day | Topics | Status | Commit |

|---|---|---|---|

| 1 | OOP, Collections, List, Set, Map, Queue concepts | Completed; queue hands-on pending | — |

| 2 | Streams, Collectors, Optional | Completed | — |

| 3 | Records, Sealed Classes, Pattern Matching, Switch Expressions | Completed | `63a67c9` |

| 4 | Concurrency, Executors, CompletableFuture, ForkJoinPool, Virtual Threads | Completed | `a52f2ae` |

| 5 | JPMS, Reflection, ServiceLoader, SPI | Completed | `31c8ad0` |

| 6 | Maven, Dependencies, Build Lifecycle, Plugins | Completed | `851bebb` |



## Revision Topics



### Day 1 — OOP and Collections

- Classes, objects, inheritance, encapsulation, abstraction, polymorphism

- equals(), hashCode(), Comparator, Comparable

- List, Set, Map and their implementations

- HashMap, TreeMap and TreeSet internals

- **Pending:** Queue, PriorityQueue and Deque hands-on



### Day 2 — Streams and Optional

- Stream operations and pipelines

- Filtering, mapping, flatMap

- Collectors, groupingBy, toMap

- Optional and null-handling practices



### Day 3 — Modern Java

- Records and immutability

- Sealed classes

- Pattern matching

- Switch expressions

- Local variable type inference

- Practical order-processing examples



### Day 4 — Concurrency

- Threads, synchronization and volatile

- AtomicInteger, CAS and ABA

- ExecutorService, Callable, Future

- CompletableFuture

- Locks, deadlocks and interruption

- BlockingQueue and concurrent collections

- ForkJoinPool and work stealing

- Virtual threads, pinning and semaphores

- **Revisit:** Virtual threads with Spring Boot, HikariCP and load testing



### Day 5 — JPMS

**Project:** `week-01-core-java/day-05-jpms/`



- Maven multi-module architecture

- module-info.java

- requires and requires transitive

- exports, opens and reflection

- ServiceLoader, uses and provides

- PaymentProvider SPI

- PaymentProviderSelector and provider caching

- Unmodifiable maps versus thread safety



**Commit:** `31c8ad0`




### Day 6 — Maven Build Engineering

**Project:** `week-01-core-java/day-06-maven/`

**Status:** Concepts, practical exercises and build verification completed. Git commit and push pending.

**Modules:**
- `order-common` — Shared `Order` record
- `order-service` — Business logic depending on `order-common`
- `order-app` — Executable application depending on `order-service`

**Concepts covered:**
- Maven coordinates, POM structure and build lifecycle
- Dependency scopes, transitive dependencies and conflict resolution
- Parent POM inheritance versus module aggregation
- `dependencyManagement` versus `pluginManagement`
- Effective POM and dependency troubleshooting
- Maven reactor commands: `-pl`, `-am`, `-amd`
- Maven Compiler, Surefire, Failsafe, Enforcer and Shade plugins
- SNAPSHOT versions, repositories and deployment concepts
- JAR/WAR packaging, manifests and Java compatibility

**Hands-on completed:**
- [x] Created a three-module Maven project
- [x] Configured parent and child POMs
- [x] Centralized dependency versions
- [x] Inspected Effective POM
- [x] Practised Maven reactor commands
- [x] Created executable shaded JAR
- [x] Inspected JAR contents and manifest
- [x] Ran application successfully using `java -jar`
- [x] Verified `mvn clean verify`
- [x] Updated `.gitignore` to exclude `dependency-reduced-pom.xml`
- [x] Committed and pushed Day 6

**Maven revision commands:**

```bash
mvn clean verify
mvn help:effective-pom -pl order-service -Doutput=effective-pom.xml
mvn clean package -pl order-common
mvn clean package -pl order-app -am
mvn clean package -pl order-common -amd
java -jar order-app/target/order-app-1.0-SNAPSHOT.jar
```

Run these commands from the `day-06-maven` directory.

**Important interview points:**
- `<parent>` provides inheritance; `<modules>` provides aggregation.
- `dependencyManagement` manages versions without declaring dependencies.
- `pluginManagement` provides plugin configuration without activating plugins.
- Maven reactor resolves sibling module dependencies in build order.
- Shade bundles application dependencies into a single JAR and can configure its main class.
- A successful Maven build doesn't automatically guarantee production runtime compatibility.

**Outstanding revision:**
- Hands-on dependency conflict troubleshooting
- Advanced Shade configurations, if required later

**Git commit:** `851bebb`

**Status:** Completed

**Revision status:** Completed; revisit during Week 6.



