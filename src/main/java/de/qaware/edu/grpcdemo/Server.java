package de.qaware.edu.grpcdemo;

import de.qaware.edu.grpcdemo.generated.HelloWorldGrpc;
import de.qaware.edu.grpcdemo.generated.HelloWorldProto;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(12345).addService(new ServerImpl()).build();

        server.start();
        System.out.println("Server is running on port 12345");

        server.awaitTermination();
    }

    private static class ServerImpl extends HelloWorldGrpc.HelloWorldImplBase {
        @Override
        public void greet(HelloWorldProto.GreetRequest request, StreamObserver<HelloWorldProto.GreetResponse> responseObserver) {
            String name = request.getName();

            HelloWorldProto.GreetResponse response = HelloWorldProto.GreetResponse.newBuilder().setResponse("Hello " + name).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
