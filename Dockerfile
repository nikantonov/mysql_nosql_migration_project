FROM java:8
WORKDIR /
ADD imseapp.jar imseapp.jar 
EXPOSE 8080
CMD ["java", "-jar", "imseapp.jar", "stack.yml"]