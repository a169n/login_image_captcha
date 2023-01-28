public class Main {
    public static void main(String[] args) {
        Authorization user1 = new Authorization("Student", "123456789012", "aitu1234");
        while (user1.getAttempt_counter() > -1) {
            if (user1.getAttempt_counter() == 0) {
                if (user1.captcha_input_check()) {
                    user1.attempt_reset();
                } else {
                    System.out.println("Our system have detected suspicious activity! Try later!");
                    break;
                }
            }
            if (user1.user_input()) {
                System.out.println("Access allowed! ");
                break;
            }
            user1.attempt_decrease();
            if (user1.getAttempt_counter() > 0) {
                System.out.println("Try again! You have left " + user1.getAttempt_counter() + " chance(-s)");
            }
        }
    }
}