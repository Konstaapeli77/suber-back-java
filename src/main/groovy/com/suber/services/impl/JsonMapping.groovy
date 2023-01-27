package com.suber.services.impl

import groovy.json.JsonSlurper

class Person {
    int id
    String firstname
    String lastname
    int age
    String address

}

class JsonMapping {



    def transform() {

        String jsonObject = '{id:1, firstname:"John", lastname:"Doe", age:24, address:"Kilterinrinne 10"}'

    }

    static void main(String[] args) {

        String jsonObject = '{ "id":1, "firstname":"John", "lastname":"Doe", "age":24, "address":"Kilterinrinne 10" }'
        def person = new Person();



        def personMap = new JsonSlurper().parseText(jsonObject);

        person = new Person(personMap);

    }

}
