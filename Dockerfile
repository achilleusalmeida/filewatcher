# The Dockerfile supports the following instructions:
# ADD	Add local or remote files and directories.
# ARG	Use build-time variables.
# CMD	Specify default commands.
# COPY	Copy files and directories.
# ENTRYPOINT	Specify default executable.
# ENV	Set environment variables.
# EXPOSE	Describe which ports your application is listening on.
# FROM	Create a new build stage from a base image.
# HEALTHCHECK	Check a container's health on startup.
# LABEL	Add metadata to an image.
# MAINTAINER	Specify the author of an image.
# ONBUILD	Specify instructions for when the image is used in a build.
# RUN	Execute build commands.
# SHELL	Set the default shell of an image.
# STOPSIGNAL	Specify the system call signal for exiting a container.
# USER	Set user and group ID.
# VOLUME	Create volume mounts.
# WORKDIR	Change working directory.

# The FROM instruction initializes a new build stage and sets the base image for subsequent instructions.
# sytax: FROM [--platform=<platform>] <image> [AS <name>]
FROM openjdk:17

# The EXPOSE instruction informs Docker that the container listens on the specified network ports at runtime.
# sytax: EXPOSE <port> [<port>/<protocol>...]
EXPOSE 8080

# The ADD instruction copies new files, directories or remote file URLs from <src> and adds them to the filesystem of the image at the path <dest>.
# ADD [OPTIONS] <src> ... <dest>
ADD build/libs/filewatcher.jar filewatcher.jar


# An ENTRYPOINT allows you to configure a container that will run as an executable.
# syntax : ENTRYPOINT ["executable", "param1", "param2"]
ENTRYPOINT [ "java","-jar","filewatcher.jar" ]