package de.qaware.edu.grpcdemo;

import de.qaware.edu.grpcdemo.generated.HelloWorldGrpc;
import de.qaware.edu.grpcdemo.generated.HelloWorldProto;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", 12345).usePlaintext().build();

        HelloWorldGrpc.HelloWorldBlockingStub helloWorldService = HelloWorldGrpc.newBlockingStub(channel);

        System.out.print("What's your name? ");
        String name = new Scanner(System.in).nextLine();

        HelloWorldProto.GreetResponse response = helloWorldService.greet(HelloWorldProto.GreetRequest.newBuilder().setName(name).build());

        System.out.println("Server says: " + response.getResponse());
    }
}
