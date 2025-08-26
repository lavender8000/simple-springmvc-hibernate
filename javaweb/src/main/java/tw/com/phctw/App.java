package tw.com.phctw;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class App {

    public static void main(String[] args) {

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        System.out.println(bc.encode("666666"));


    }
}
