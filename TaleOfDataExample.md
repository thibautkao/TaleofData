1- Nous avons créé une classe generique pour eviter le fort couplage avec un type de donne particulier.
2- Notre programme utilise une architecture en couche :
   bean : contient nos POJO ou bean
   service : contient la classe de manipulation et de transformation du fichier CSV
   la classe Program qui represente le programme principal

3- Nous avons les tests unitaires puor s'assurer que notre classe DataService fonctionne bien


Explication des couches : 
Bean : nous avons deux POJO, un generique GenericData et un autre Data qui herite du premier.
Service : nous avons une classe generique qui a trois fonctions principales a savoir : 
 une fonction de lecture du fichier CSV
 une fonction de transformation des donnees du fichier CSV. Pour avoir un controle sur le type de transformation a effectuer sur nos POJO, nous prenons
     parametre, un interface fonctionnelle : Function<T, T>
 une fonction de sauvegarde des donnees transformees dans un fichier

Main : le programme principal qui fait appel aux fonctions pour traiter notre fichier passe en parametre