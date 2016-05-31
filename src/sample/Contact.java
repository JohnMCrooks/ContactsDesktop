package sample;

/**
 * Created by johncrooks on 5/31/16.
 */
public class Contact {
    String name;
    String email;
    String phone;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return  name + ", " + phone +
                ", "+ email;
    }
}
