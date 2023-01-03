### How to upload artifacts ( jars, csv, sql etc ) to S3

Build the jar in service-creator folder:

```mvn clean package spring-boot:repackage```

From the Target Folder:

```aws s3 cp target/demo-0.0.1-SNAPSHOT.jar s3://dihdemo/2.1/common/apps/```

And setting acl:
```aws s3api put-object-acl --bucket dihdemo --key 2.1/common/apps/demo-0.0.1-SNAPSHOT.jar --acl public-read```