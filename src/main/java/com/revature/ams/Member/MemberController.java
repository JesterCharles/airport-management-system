package com.revature.ams.Member;

// TODO: REVIEW ME

import java.util.Scanner;

public class MemberController {
    private final Scanner scanner;
    private final MemberService memberService;
    private int count = 0;

    public MemberController(Scanner scanner, MemberService memberService) {
        this.scanner = scanner;
        this.memberService = memberService;
    }

    public void register(){
        System.out.println("Please enter first name: ");
        String firstName = scanner.next();

        System.out.println("Please enter last name: ");
        String lastName = scanner.next();

        System.out.println("Please enter email: ");
        String email = scanner.next();

        Member.MemberType memberType = Member.MemberType.valueOf("PASSENGER");

        System.out.println("Enter password: ");
        String password = scanner.next();

        Member newMember = new Member(count,firstName, lastName, email, memberType, password);
        memberService.create(newMember);
        count++;
    }

    public void update(){
        System.out.println("Enter the ID of the member to update: ");
        int memberId = scanner.nextInt();

        Member memberToUpdate = memberService.findById(memberId);

        if (memberToUpdate == null) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        System.out.println("What information do you want to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("4. Password");
        System.out.println("5. Cancel");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter new first name: ");
                String newFirstName = scanner.next();
                memberToUpdate.setFirstName(newFirstName);
                break;
            case 2:
                System.out.println("Enter new last name: ");
                String newLastName = scanner.next();
                memberToUpdate.setLastName(newLastName);
                break;
            case 3:
                System.out.println("Enter new email: ");
                String newEmail = scanner.next();
                memberToUpdate.setEmail(newEmail);
                break;
            case 4:
                System.out.println("Enter new password: ");
                String newPassword = scanner.next();
                memberToUpdate.setPassword(newPassword); // Remember to implement secure password storage
                break;
            case 5:
                System.out.println("Update cancelled.");
                return;
            default:
                System.out.println("Invalid choice.");
        }

        memberService.update(memberToUpdate); // Call update method in MemberService
        System.out.println("Member information updated successfully!");
    }

}
