APP
	http://localhost:8080/ConnectToGrow/

login/FK-Portal 
	http://localhost:8080/ConnectToGrow/ctg
		=> ui/login.html

Register
	http://localhost:8080/ConnectToGrow/ctg/register?name=Frankfurter%20Kompressoren%20AG
		=> ui/register.html
		Daten: register.csv (name)

Landing
	http://localhost:8080/ConnectToGrow/ctg/landing
		=> ui/landing-page.html
		=> ui/landingCard.html
		Daten: Kooperationen.csv

	LIKE/DISLIKE: 
		http://localhost:8080/ConnectToGrow/ctg/landing?action=like&like=Vemcon%20und%20Kinshofer
		

Likes
	localhost:8080/ConnectToGrow/ctg/likes
		=> ui/likes-page.html
		=> ui/landingCard.html

Matches
	http://localhost:8080/ConnectToGrow/ctg/matches
		=> ui/macthes.html
		=> ui/card.html
		Daten: Macthes.csv (name)

	Macth/dismatch 
		http://localhost:8080/ConnectToGrow/ctg/matches?action=match&match=Aaron%20AI

MatchFound
	http://localhost:8080/ConnectToGrow/ctg/matchesFound
		=> ui/matchesFound.html
		=> ui/card.html

StartUps
	http://localhost:8080/ConnectToGrow/ctg/profile?name=Maschin.io
		=> ui/profil-card.html


		