
import java.util.*;

class AgeInvalidException extends Exception {

    public AgeInvalidException(String msg) {

        super(msg);

    }

}

class InvalidEmailException extends Exception {

    public InvalidEmailException(String msg) {

        super(msg);

    }

}

class Contact {

    private int number;

    private String name;

    private String email;

    private int age;

    public Contact(int number, String name, String email, int age) {

        this.number = number;

        this.name = name;

        this.email = email;

        this.age = age;

    }

    public int getNumber() {

        return number;

    }

    public String getName() {

        return name;

    }

    public String getEmail() {

        return email;

    }

    public int getAge() {

        return age;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public void setAge(int age) {

        this.age = age;

    }

    @Override

    public String toString() {

        return "Number: " + number +

               ", Name: " + name +

               ", Email: " + email +

               ", Age: " + age;

    }

}

class ContactManager {

    Scanner sc = new Scanner(System.in);

    void addContacts(HashMap<Integer, Contact> contacts, int n) {

        for (int i = 0; i < n; i++) {

            try {

                int number = sc.nextInt();

                sc.nextLine();

                String name = sc.nextLine();

                String email = sc.nextLine();

                int age = sc.nextInt();

                if (contacts.containsKey(number)) {

                    System.out.println("Contact number already exists. Skipping contact.");

                    continue;

                }

                if (age < 0) {

                    throw new AgeInvalidException("Age cannot be negative");

                }

                if (!(email.contains("@") && email.endsWith(".com"))) {

                    throw new InvalidEmailException("Invalid Email");

                }

                Contact c = new Contact(number, name, email, age);

                contacts.put(number, c);

                System.out.println("Contact added successfully");

            } catch (AgeInvalidException | InvalidEmailException e) {

                System.out.println(e.getMessage());

            }

        }

    }

    void removeContactsByName(HashMap<Integer, Contact> contacts, String name) {

        boolean removed = false;

        Iterator<Map.Entry<Integer, Contact>> it =

                contacts.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, Contact> entry = it.next();

            if (entry.getValue().getName().equalsIgnoreCase(name)) {

                it.remove();

                removed = true;

            }

        }

        if (removed) {

            System.out.println("Contacts removed successfully for name: " + name);

        } else {

            System.out.println("No contacts found with name: " + name);

        }

    }

    void editContact(HashMap<Integer, Contact> contacts,

                     int number, Contact contact) {

        if (contacts.containsKey(number)) {

            contacts.put(number, contact);

            System.out.println("Contact updated successfully");

        } else {

            System.out.println("Contact number not found. Update failed.");

        }

    }

    void displayContactThreshold(HashMap<Integer, Contact> contacts,

                                 int startAge, int endAge) {

        if (contacts.isEmpty()) {

            System.out.println("No contacts available");

            return;

        }

        boolean found = false;

        for (Contact c : contacts.values()) {

            if (c.getAge() >= startAge && c.getAge() <= endAge) {

                if (!found) {

                    System.out.println("Contacts with age between "

                            + startAge + " and " + endAge + ":");

                }

                System.out.println(c);

                found = true;

            }

        }

        if (!found) {

            System.out.println("No contacts found in the age range "

                    + startAge + " to " + endAge);

        }

    }

    void displayContactThreshold(HashMap<Integer, Contact> contacts,

                                 String nameFilter) {

        if (contacts.isEmpty()) {

            System.out.println("No contacts available");

            return;

        }

        boolean found = false;

        for (Contact c : contacts.values()) {

            if (c.getName().startsWith(nameFilter)) {

                if (!found) {

                    System.out.println("Contacts starting with '" +

                            nameFilter + "':");

                }

                System.out.println(c);

                found = true;

            }

        }

        if (!found) {

            System.out.println("No contacts found starting with '" +

                    nameFilter + "'");

        }

    }

}

public class ContactManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Contact> contacts = new HashMap<>();

        ContactManager manager = new ContactManager();

        int size = sc.nextInt();

        manager.addContacts(contacts, size);

        sc.nextLine();

        String removeName = sc.nextLine();

        manager.removeContactsByName(contacts, removeName);

        int number = sc.nextInt();

        sc.nextLine();

        String name = sc.nextLine();

        String email = sc.nextLine();

        int age = sc.nextInt();

        Contact updatedContact =

                new Contact(number, name, email, age);

        manager.editContact(contacts, number, updatedContact);

        int ageThreshold = sc.nextInt();

        manager.displayContactThreshold(

                contacts, ageThreshold, ageThreshold + 10);

        sc.nextLine();

        String nameFilter = sc.nextLine();

        manager.displayContactThreshold(contacts, nameFilter);

    }

}
 