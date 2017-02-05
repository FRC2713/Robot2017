# Robot2017
The code for iRaiders 2713's FRC Robot

## Importing into an IDE
Gradle will automatically create IDE project files when necessary.

### Importing into IntelliJ IDEA
1. Go to `File > New > Project from Version Control > Git`
2. Type the repository URL
	* HTTPS: https://github.com/iraiders/Robot2017.git
	* SSH: git@github.com:iraiders/Robot2017.git
3. Click Clone
4. Use the pop-up in the bottom-right corner (or the Event Log if you do not
catch it) to import the project using Gradle.

### Importing into Eclipse
1. Clone the repository using the method of your choice.
2. Open the command line and point it to the root directory of the repository.
3. Run the command `gradlew eclipse`.
4. Use the Eclipse project wizard to import the repository as an
'Existing Project'.

### Updating Dependencies
When dependencies are updated, the IDE will not automatically refresh them.
Instead, you must sync them yourself.

#### IntelliJ IDEA
1. Go to `View > Tool Windows > Gradle`
2. Click the Sync button (left of the + sign).
3. Wait for dependencies to update.

#### Eclipse
1. **Close Eclipse.** Seriously, you have to.
2. Rerun `gradlew eclipse`.
3. Reopen Eclipse.

## Deploying

Passing the arguments `build deploy` to gradle will build the project and search
for the RoboRIO on the network to deploy. Use either the command line (`gradlew
build deploy`) or your IDE's run configurations to do so.
