MrChecker template
-------------------
This branch features a template for small (less than 3-5 jobs) project.

## Setup manual
1. Prerequisites
	* Aquire linux machine with 6vCPU and 8GB of RAM (rough estimates - but changing this values will affect your performance, more on it in next section). 320GB of HDD should be enought.
	* Install docker on it.
	* Setup docker-compose.
1. Config
	* Put a docker folder from this project to your machine.
	* Go to `docker/docker-compose.yml`
	* Adjust replicas numbers for selenium agents.
	* Setup PostgreSQL password via `POSTGRES_PASSWORD` variable.
	* Go there and run `docker-compose up` now the magic happens.
1. Jenkins initial setup
	* Wait until jenkins image is downloaded and built, wait for selenium and gitea images.
	* Go to `./jenkins/secrets/initialAdminOassword`
	* Now when the stack is up and runing, from your PC go to `host:8888` which is default jenkins port and fill the form in with the value of of initial admin pass *uired in the previous step.* Select to install sugested plugins
	* Connect agents
1. Gitea initial setup
	* From your PC access `host:3000` which is default Gitea port.
	* Select register. You should be prompted with `Initial Configuration` form.
	* Select database to PostgreSQL adress `gitea-db:5432`
	* Provide it with PostgreSQL password from Config step.
	* Gitea will restart.
	* Register an user who will become an admin.
1. Setting up a project (gitea part)
	* Create empty new repository from Gitea UI
	* On your PC copy `./template` content from this project
	* Go to newly created project and run `git init`, then `git add -A`, next `git commit -m "initial commit"`.
	* Now push this to gitea with `git remote add origin http://<host>:3000/<user>/<yourprojectname>.git`, `git push -u origin master` (the commands are listed in Gitea UI)
	* Create technical user for jenkins
1. Setting up a project (jenkins part)
	* From jenkins UI Create Item, multibranch pipeline
	* In section Branch Sources select git.
	* As project repository add address to your project (it might be necessary to replace `<host>` with `gitea-web`)
	* Create new credentials and set them to you tech user
	* Save
1. Finishing actions
	* Configure jenkins security
	* Adjust Jenkinsfile

### Hardware resources
Below I just wanted to show you how I calculated the values, feel free to mix match and scale it.
#### Jenkins
<https://www.jenkins.io/doc/book/scaling/hardware-recommendations/>
There are no examples here. But they state the minimum amount of ram needed is 200MB (I consider this bare minimum), so 1 GB would be resonable amount. CPU is not mentioned at all so we can asume it's more Memory bound, thus 1vCPU.

#### Selenium
<https://github.com/SeleniumHQ/docker-selenium/issues/549>
<https://github.com/SeleniumHQ/docker-selenium>
No clear answer but we can assume one selenium agent can consume from 0.5GB to 2GB of RAM memory, and some CPU. So for basic grid with 1 Firefox and 1 Chrome agent we will need 3GB of ram and 2vCPU. We also need something for hub, 1GB and 0.5vCPU should be enought. Total 4GB and 2.5vCPU.

#### Gitea
<https://docs.gitea.io/en-us/#system-requirements>
2vCPUs and 1GB of RAM.

#### System and docker
For sure there is system and docker overhead so until this point we have 6GB of ram and 5.5vCPU. To give it some room lets round that up to 8GB and 6vCPU.

#### Jenkins agents
Its best to have jenkins agents (and selenium agents) on separate VM. For jenkins agent I can sugest 1vCPU and 1GB of RAM rule.