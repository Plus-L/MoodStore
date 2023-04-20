FROM mysql/mysql-server:8.0.31

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./moodstore.sql /docker-entrypoint-initdb.d

COPY ./moodstore_codegen.sql /docker-entrypoint-initdb.d

COPY ./moodstore_config.sql /docker-entrypoint-initdb.d

COPY ./moodstore_job.sql /docker-entrypoint-initdb.d
