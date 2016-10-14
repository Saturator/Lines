##Rakennekuvaus
Ohjelma rakentuu kolmesta paketista: Game, engine ja math. Game on kaikista
korkeimman tason rajapinta pelin muokkaamiseen. Tänne on tarkoitus pystyä 
luomaan kohtuu vapaasti omia luokkiaan ja viitata nillä engine taikka math 
paketteihin. Tämä paketti myös sisältää pääluokan Game jossa peliluuppi 
tällä hetkellä pyörii. Peliluuppi toimii fixed timestepin avulla pelkästään,
joka meinaa sitä, että meidän FPS pitäisi staattinen suurimmaksi osaksi.
Jos lagia ilmenee, niin fixed timestepin deltatime osio käy 'missatut' framet
läpi. Luomme tässä luokassa myös uuden peli-ikkunan sekä uuden kokonaisen 
peliprojektin.

Engine paketti pitää sisällään pelin yleiseen rakenteeseen liittyvän putkiston.
GameWindow on erillinen komponentti, mutta muut osat ovat hierarkisesti riippuvaisia.
GameProject on ylimmän tason luokka, joka sisältää lähes kaiken peliin liittyvän
informaation. Voit siis kutsua projektin kautta vaikkapa spesifiä GameObject
olioa tai jopa mennä sen RigidBodyy math paketin puolelle jos on tarve. Tärkein
GameProjectin sisältämä data on kuitenkin kaikki pelin GameScenet. GameScene 
sisältää aina esim. tietyn tason tai kohdan pelistä. Näiden avulla voit jakaa
peliruutuja selkeämpiin yksikköihin jotka kaikki sisältävät omat GameObjectinsa.
GameObject luokka sisältää yhden pelissä olevan objektin, kuten vaikka pallon
tai boksin. GameObjectiin ei ole pakko liittää RigidBodya, mutta jos haluat
fysiikan vaikuttavan objektiin niin on se lisättävä.

PhysicsBody on taas Math pakkauksessa. PhysicsBody on yläluokka kaikille objekteille
johon moottorinsisäiset fysiikanlait voivat vaikuttaa. Tällä hetkellä toimiva
fysiikkaobjekteja on kaksi, BoxBody ja CircleBody. BoxBody on neliön muotoinen
objekti ja CircleBody on ympyrän muotoinen objekti.
Math pakkaus sisältää kaikkeen fysiikkaan
liittyvän matikan. Pääasiassa tämä on vektorimatikkaa, eli lasketaan ja
muutetaan pisteiden paikkoja kaksiulotteisessa avaruudessa. Ympyrän voi määritellä
yhtenä pisteenä 2D avaruudessa jolla on tietty säde ja neliön voi määritellä
neljänä erillisenä pisteenä 2D avaruudessa. Vector2 luokka vastaa näitä 2D
pisteitä, joka meinaa, että kyseinen luokka sisältää arvot x ja y. X ruudun vasemmasta
sivusta oikealle mitatut pikselit ja y on ruudun ylälaidasta alalaitaan mitatut
pikselit.
