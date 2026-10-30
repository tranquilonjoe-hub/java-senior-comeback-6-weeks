# Senior Java Developer Comeback — Revision Index



**Repository:** java-senior-comeback-6-weeks  

**Java:** JDK 21  

**Current milestone:** Day 7 — Week 1 Gap Closure and Integrated Mini-Project



## Week 1 — Core Java and Modern Java



| Day | Topics | Status | Commit |

|---|---|---|---|

| 1 | OOP, Collections, List, Set, Map, Queue concepts | Completed; queue hands-on pending | — |

| 2 | Streams, Collectors, Optional | Completed | — |

| 3 | Records, Sealed Classes, Pattern Matching, Switch Expressions | Completed | `63a67c9` |

| 4 | Concurrency, Executors, CompletableFuture, ForkJoinPool, Virtual Threads | Completed | `a52f2ae` |

| 5 | JPMS, Reflection, ServiceLoader, SPI | Completed | `31c8ad0` |

| 6 | Maven, Dependencies, Build Lifecycle, Plugins | Completed early (original Day 8) | `851bebb` |

| 7 | Week 1 Gap Closure, Collections Hands-on, Integrated Mini-Project | Not started | — |



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

**Status:** Completed — build verified, committed and pushed.

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

**Revision status:** Completed; revisit during Week 6.



## Day 7 — Week 1 Review: Queues and Concurrency

**Status:** In progress — Queue exercises completed; integrated Java 21 mini-project and Week 1 revision audit pending.

### Milestone 1 — CircularQueue

**Commit:** `9cd7f68`

**Files:**
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/CircularQueue.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/CircularQueueDemo.java`

**Concepts:**
- Fixed-capacity circular array implementation
- Head, tail, size, and capacity management
- FIFO operations: `offer()`, `poll()`, `peek()`
- Wraparound using modulo arithmetic
- Full and empty queue handling
- Input validation and boundary testing

**Revision points:**
- Explain circular queues and why wraparound is needed.
- Explain time complexity of queue operations.
- Trace head and tail movement manually.

### Milestone 2 — PriorityQueue and Deque

**Commit:** `06bfc81`

**Files:**
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/PriorityQueueDemo.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/Order.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/OrderPriorityQueueDemo.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/DequeDemo.java`

**Concepts:**
- Min-heap and max-heap ordering
- PriorityQueue insertion and removal complexity
- Custom comparators with multiple sorting criteria
- Priority ordering with FIFO tie-breaking
- Priority starvation
- Deque operations for FIFO and LIFO
- ArrayDeque versus LinkedList
- Thread-safety limitations of PriorityQueue and ArrayDeque

**Revision points:**
- Explain why PriorityQueue iteration is not sorted.
- Explain heap insertion and removal.
- Explain comparator chaining and `reversed()`.
- Compare Queue, Deque, and PriorityQueue.

### Milestone 3 — BlockingQueue and Graceful Shutdown

**Commit:** `61a5cf3`

**Files:**
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/BlockingQueueDemo.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/BlockingQueueEmptyDemo.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/BlockingQueueTimeoutDemo.java`
- `week-01-core-java/day-07-week1-review/src/main/java/com/tranquilonjoe/day07/MultiProducerConsumerDemo.java`

**Concepts:**
- Bounded BlockingQueue
- `put()` and `take()` blocking behavior
- Timed `offer()` and `poll()`
- Timeout measurement using `System.nanoTime()`
- Multiple producers and consumers
- FIFO dequeue order versus processing completion order
- Thread coordination with `start()` and `join()`
- Poison-pill shutdown mechanism
- Interruption handling
- Deadlock risks with bounded queues

**Revision points:**
- Explain how ArrayBlockingQueue coordinates producers and consumers.
- Compare blocking, non-blocking, and timed queue operations.
- Explain why a timed offer can return before its timeout.
- Explain why consumers must start before joining producers in a bounded-queue scenario.
- Explain why shutdown signals should be submitted after producers finish.
- Explain why one poison pill per consumer is needed in this design.

### Pending Day 7 Work

- [ ] Integrated Java 21 order-processing mini-project
- [ ] Week 1 revision audit
- [ ] Final Day 7 commit and revision checkpoint

**Revision status:** Queue and concurrency exercises completed; full Day 7 revision pending.

#### Part 1 — Collections and Data Structures

- [x] Queue interface and FIFO behaviour
- [x] ArrayDeque as a Queue
- [ ] PriorityQueue and comparator-based ordering
- [ ] Deque operations (both ends)
- [ ] ArrayDeque versus LinkedList
- [ ] BlockingQueue versus ordinary Queue
- [ ] Time complexity and performance tradeoffs
- [ ] Practical scenarios: task processing, priority scheduling and bounded producer-consumer systems

#### Part 2 — Week 1 Integrated Mini-Project

Build a Java 21 order-processing application combining:

- [ ] Records for immutable order data
- [ ] Collections for order storage and processing
- [ ] Streams for filtering, grouping and aggregation
- [ ] Optional for missing-value handling
- [ ] Pattern matching and switch expressions
- [ ] ExecutorService or CompletableFuture for concurrent processing
- [ ] Appropriate exception handling and input validation
- [ ] Practical tests and output verification
- [ ] Git commit and push

#### Part 3 — Original Week 1 Coverage Audit

- [ ] OOP principles and polymorphism
- [ ] List, Set, Map and Queue
- [ ] Streams and functional programming
- [ ] Optional and null-handling practices
- [ ] `var`, records, sealed classes and text blocks
- [ ] Pattern matching and switch expressions
- [ ] Java modules (JPMS)
- [ ] Thread, Runnable, ExecutorService and CompletableFuture
- [ ] Java 17/20 features reviewed in the context of JDK 21
- [ ] Outstanding questions documented

#### Part 4 — Review and Revision

- [ ] Explain concepts from fundamentals
- [ ] Compare alternatives and production tradeoffs
- [ ] Review key data structure complexities
- [ ] Identify remaining weaknesses
- [ ] Update REVISION_INDEX.md
- [ ] Confirm readiness for Week 2

**Git commit:** Pending

**Revision status:** Pending

**Next milestone after Day 7:** Day 9 — Gradle and Kotlin DSL.


## Roadmap Alignment and Coverage Tracking

**Master syllabus:** Original six-week Senior Java Developer Comeback Roadmap.

**Learning approach:** Fundamentals → internals → practical implementation → production scenarios → tradeoffs → interview preparation → revision.

**Coverage policy:**
- Original roadmap topics are mandatory minimum coverage.
- Additional deep dives are encouraged where relevant.
- A concept being explained does not automatically mean its practical exercise is complete.
- Missing topics must be tracked explicitly rather than silently skipped.
- Day numbers reflect the original schedule where possible; completed work is never discarded or unnecessarily repeated.

### Week 1 — Outstanding Work

- [ ] Queue, PriorityQueue and Deque hands-on
- [ ] Combined Week 1 mini-project
- [ ] Java 17/20 feature coverage audit
- [ ] Week 1 final review

### Week 2 — Planned Work

- [x] Original Day 8 — Maven and dependency management (completed as actual Day 6; commit `851bebb`)
- [ ] Day 9 — Gradle, Kotlin DSL and Maven-to-Gradle conversion
- [ ] Day 10 — JUnit 5 and parameterized testing
- [ ] Day 11 — Mockito, mocks, stubs and spies
- [ ] Day 12 — Testcontainers and database integration testing
- [ ] Day 13 — GitHub Actions and Jenkins CI/CD fundamentals
- [ ] Day 14 — Integrated build, testing and CI mini-project

### Future Practical Exercises

- [ ] Spring Boot 3 virtual threads versus platform threads
- [ ] Load testing: throughput, latency and error rates
- [ ] HikariCP connection-pool saturation
- [ ] Downstream API timeouts and concurrency limits
- [ ] Revisit any outstanding production scenarios identified during revision

### Progress Verification Rules

A milestone is marked **Completed** only when its required concepts and hands-on work have been verified.

A milestone is marked **Partially completed** when practical exercises or required topics remain.

Git commits, project paths, revision notes and outstanding tasks must be recorded accurately.

## Revision Commands

- **Quick revision:** Summarize the topic with key concepts and common mistakes.
- **Deep revision:** Teach from fundamentals with practical examples and tradeoffs.
- **Interview mode:** Ask one question at a time, without hints.
- **Coding mode:** Give a hands-on exercise and review my solution.
- **Production mode:** Explain real-world usage, limitations and alternatives.
- **Coverage audit:** Compare completed topics and hands-on work against the original roadmap and identify gaps.

## Milestone Completion Checklist

- [ ] Concepts explained and understood
- [ ] Practical code completed
- [ ] Interview questions reviewed
- [ ] Maven build or relevant tests verified
- [ ] Git commit and push completed
- [ ] Revision Index updated
- [ ] Outstanding topics recorded

