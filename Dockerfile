################################################
#             JAVA MICROSERVICE IMAGE          #
################################################
FROM nexus.gboteam.ru/redos-ubi7-minimal:base
USER root
# Set the locale to UTF-8
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8

# Set timezone
ENV TZ=Europe/Moscow

LABEL "entire-pool" "entire-pool"
LABEL description="entire-pool nano service"

# Create the home directory for the new app user.
RUN mkdir -p /home/app

# Create an app user so our program doesn't run as root.
RUN groupadd -r app && useradd -r -g app -d /home/app -s /sbin/nologin -c "Docker image user" app

# Set the home directory to our app user's home.
ENV HOME=/home/app
ENV APP=entire-pool.jar
ENV APP_HOME=/home/app/entire-pool

## SETTING UP THE APP ##
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

# Chown all the files to the app user.
RUN chown app:app $APP_HOME

# Change to the app user.
USER app:app

# Copy in the application code.
COPY target/entire-pool.jar $APP_HOME/$APP

ENTRYPOINT exec java $JAVA_OPTIONS -Djava.awt.headless=true -Djava.security.egd=file:/dev/urandom -jar $APP_HOME/$APP