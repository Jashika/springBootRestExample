package com.springbootWithJPA.springBootRestExample;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String firstName;
        private String lastName;
        private String emailId;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmailId() {
                return emailId;
        }

        public void setEmailId(String emailId) {
                this.emailId = emailId;
        }

        @Override
        public String toString() {
                return "Employee{" +
                        "id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", emailId='" + emailId + '\'' +
                        '}';
        }
}
