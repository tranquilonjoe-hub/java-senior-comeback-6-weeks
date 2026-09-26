package com.tranquilonjoe.day01;

import java.util.*;

public class Day01Collections {

    public void printCompleteList(List<Person> list) {
        for (Person p : list) {
            System.out.println(p);
        }
    }

    public void getPersonByIndex(List<Person> list, int index) {
        Person person = list.get(index);
        System.out.println("Person at index " + index + ":" + person);
    }

    public void getPersonById(List<Person> list, int id) {
        for (Person p : list) {
            if (p.getId() == id) {
                System.out.println(p);
                break;
            }
        }
    }

    public void setPersonAge(List<Person> list, String name, int age) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                p.setAge(age);
                break;
            }
        }
    }

    public void removeByIdFromList(List<Person> list, int id) {
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public void sortByName(List<Person> list) {
        list.sort(Comparator.comparing(Person::getName));
    }

    public void sortByAge(List<Person> list) {
        list.sort(Comparator.comparingInt(Person::getAge));
    }

    public void sortByAgeAndName(List<Person> list) {
        list.sort(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName));
    }

    public void printCompleteSet(Set<Person> set) {
        for (Person p : set) {
            System.out.println(p);
        }
    }

    public void updateMapByPersonId(Map<Integer, Person> map, int id, String name, int age){
        for(Map.Entry<Integer,Person> entry: map.entrySet()){
            if(entry.getValue().getId() == id){
                entry.setValue(new Person(id,name,age));
                break;
            }
        }
    }

    public void getByKeyFromMap(Map<Integer, Person> map, int key){
        System.out.println("Person with map key "+key+":"+map.get(key));
    }

    public List<Integer> getPersonMapKeys(Map<Integer, Person> map){
        return new ArrayList<>(map.keySet());
    }
    public List<Person> getPersonMapValues(Map<Integer, Person> map){
        return new ArrayList<>(map.values());
    }

    public void printPersonMap(Map<Integer, Person> map){
        for(Map.Entry<Integer,Person> persons: map.entrySet()){
            System.out.println("key ="+persons.getKey()+", Value ="+persons.getValue());
        }
    }

    public boolean containsKey(Map<Integer, Person> map, int key){
        return map.containsKey(key);
    }

    public void removeFromMapByPersonId(Map<Integer, Person> map, int id){
        Iterator<Map.Entry<Integer,Person>> iterator = map.entrySet().iterator() ;
        while(iterator.hasNext()){
            Map.Entry<Integer,Person> entry= iterator.next();
            if(entry.getValue().getId() == id){
                iterator.remove();
                break;
            }
        }
    }
    public void removeByMapKey(Map<Integer, Person> map, int key){
        map.remove(key);
    }

    public void pollTheQueue(Queue<Person> queue){
        while(!queue.isEmpty()){
            System.out.println("Current front value :"+queue.peek());
            Person p=queue.poll();
            System.out.println("Removing :"+p);
        }
        System.out.println("Final queue :"+queue);
    }

    public void pollThePriorityQueue(PriorityQueue<Person> pq){
        while(!pq.isEmpty()){
            Person p=pq.poll();
            System.out.println("Removing :"+p);
        }
        System.out.println("Final queue :"+pq);
    }

    public static void main(String[] args) {

        Day01Collections collections = new Day01Collections();

        Person person1 = new Person(1, "Nirmal", 38);
        Person person2 = new Person(2, "Alice", 28);
        Person person3 = new Person(3, "Bob", 35);
        Person person4 = new Person(4, "Charlie", 25);
        Person person5 = new Person(5, "David", 35);

        //List Implementation
        System.out.println("_______LIST__________");
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        collections.printCompleteList(personList);
        collections.getPersonByIndex(personList, 2);
        collections.getPersonById(personList, 4);
        collections.setPersonAge(personList, "Bob", 36);
        collections.removeByIdFromList(personList, 2);
        collections.printCompleteList(personList);
        System.out.println("People remaining in List: " + personList.size());
        collections.sortByName(personList);
        System.out.println("Sorted List by name");
        collections.printCompleteList(personList);
        collections.sortByAge(personList);
        System.out.println("Sorted List by age");
        collections.printCompleteList(personList);

        Person person6 = new Person(6, "Aaron", 35);
        personList.add(person6);

        collections.sortByAgeAndName(personList);
        System.out.println("Sorted List by age and name");
        collections.printCompleteList(personList);

        // Set implementation
        System.out.println("_______SET__________");

        Set<Person> personSet = new HashSet<>();
        personSet.add(person1);
        personSet.add(person2);
        personSet.add(person3);
        personSet.add(person4);
        personSet.add(person5);
        personSet.add(person6);

        Person person7 = new Person(1, "Jose", 99);
        personSet.add(person7);
        collections.printCompleteSet(personSet);
        System.out.println("Size of set :" + personSet.size());
        boolean added = personSet.add(
                new Person(1, "Another Nirmal", 50)
        );
        System.out.println("Value of Added after adding Another Nirmal:" + added);

        boolean contains = personSet.contains(
                new Person(1, "XYZ", 100)
        );
        System.out.println("Contains ID 1: " + contains);

        // TreeSet implementation
        System.out.println("___________TreeSet________");
        Set<Person> sortedSet = new TreeSet<>(Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getName));
        sortedSet.add(person1);
        sortedSet.add(person2);
        sortedSet.add(person3);
        sortedSet.add(person4);
        sortedSet.add(person5);
        sortedSet.add(person6);


        Person person8 = new Person(100, "David", 35);
        Person person9 = new Person(200, "David", 35);
        sortedSet.add(person8);
        sortedSet.add(person9);
        collections.printCompleteSet(sortedSet);

        //Map implementation
        System.out.println("________Map_________");
        Map<Integer,Person> personMap =  new HashMap<>();
        personMap.put(person1.getId(),person1);
        personMap.put(person2.getId(),person2);
        personMap.put(person3.getId(),person3);
        personMap.put(person4.getId(),person4);
        personMap.put(person5.getId(),person5);
        personMap.put(person6.getId(),person6);

        collections.printPersonMap(personMap);

        collections.getByKeyFromMap(personMap,3);
        collections.updateMapByPersonId(personMap,3,"Robert",37);
        List<Integer> keys=collections.getPersonMapKeys(personMap);
        System.out.println("key values : "+keys);
        List<Person> values=collections.getPersonMapValues(personMap);
        System.out.println("Values : "+values);
        collections.printPersonMap(personMap);
        boolean status = collections.containsKey(personMap,5);
        System.out.println("Is key exists :"+status);
        collections.removeFromMapByPersonId(personMap,2);
        collections.printPersonMap(personMap);
        System.out.println("Map size :"+personMap.size());

        Person oldPerson =
                personMap.put(1, new Person(100, "Someone Else", 50));
        System.out.println("oldPerson :"+oldPerson);
        collections.printPersonMap(personMap);

        collections.removeFromMapByPersonId(personMap,100);
        collections.removeByMapKey(personMap,4);

        System.out.println("_____after removing_____");
        collections.printPersonMap(personMap);

        //Queue / Deque
        System.out.println("________QUEUE / DEQUE________");

        Queue<Person> queue = new ArrayDeque<>();
        queue.offer(person1);
        queue.offer(person2);
        queue.offer(person3);
        queue.offer(person4);
        System.out.println("Front person of the queue: "+queue.peek());
        //remove front person
        queue.poll();
        System.out.println("Front person of the queue after poll: "+queue.peek());
        queue.offer(person5);
        collections.pollTheQueue(queue);

        Deque<Person> deque = new ArrayDeque<>();
        deque.offerFirst(person1);
        deque.offerLast(person2);
        deque.offerFirst(person3);
        deque.offerLast(person4);

        System.out.println("Front person of the deque: "+deque.peekFirst());
        System.out.println("Last person of the deque: "+deque.peekLast());
        System.out.println("Remove the first person from the deque: "+deque.pollFirst());
        System.out.println("Remove the last person from the deque: "+deque.pollLast());
        System.out.println("Final deque: "+deque);

        //Priority Queue

        System.out.println("__________Priority Queue___________");
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Person::getAge));
        priorityQueue.offer(person1);
        priorityQueue.offer(person2);
        priorityQueue.offer(person3);
        priorityQueue.offer(person4);
        priorityQueue.offer(person5);

        System.out.println("Front of the queue: "+priorityQueue.peek());
        collections.pollThePriorityQueue(priorityQueue);
    }
}
