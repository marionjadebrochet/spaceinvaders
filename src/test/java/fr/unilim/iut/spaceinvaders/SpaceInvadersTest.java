
package fr.unilim.iut.spaceinvaders;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;


public class SpaceInvadersTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {

		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n", spaceinvaders.toString());
	}

	//TESTS POUR LE VAISSEAU
	
	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......V.......\n", spaceinvaders.toString());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}	
	}
	   
	    @Test
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "....VVV........\n" + 
	       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	    
	   @Test
		public void VaisseauImmobile_DeplacerVaisseauVersLaGauche() {
			
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
			spaceinvaders.deplacerVaisseauVersLaGauche();
			
			assertEquals("" + 
			"...............\n" + 
			"...............\n" +
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"VVV............\n" + 
			"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	   
	   @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	   
	   @Test
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	   
	   @Test
		public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
			
		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
			spaceinvaders.deplacerVaisseauVersLaDroite();
			assertEquals("" + 
			"...............\n" + 
			"...............\n" +
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"............VVV\n" + 
			"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}

	   @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }

	   //TESTS POUR LE MISSILE
	   
	     @Test
	     public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	     
	     @Test
	     public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

	 	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	 	   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

	 	   spaceinvaders.deplacerMissile();
	 	   
	        assertEquals("" + 
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" + 
	        ".......MMM.....\n" + 
	        ".......MMM.....\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        ".....VVVVVVV...\n" + 
	        ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	     
	     
	     @Test
	     public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

	  	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	  	   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
	  	   for (int i = 1; i <=6 ; i++) {
	  		   spaceinvaders.deplacerMissile();
	  	   }
	  	   
	  	   spaceinvaders.deplacerMissile();
	  	   
	         assertEquals("" +
	         "...............\n" + 
	         "...............\n" +
	         "...............\n" + 
	         "...............\n" +
	         "...............\n" +
	         "...............\n" + 
	         "...............\n" +
	         "...............\n" + 
	         ".....VVVVVVV...\n" + 
	         ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	     
	     //TESTS POUR ENVAHISSEUR
	     
	 	@Test
		public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {

			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(7,9), 1);
			assertEquals("" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			".......E.......\n", spaceinvaders.toString());
		}

		@Test
		public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(15,9), 1);
				fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(-1,9), 1);
				fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
				fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,-1), 1);
				fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

		}

		@Test
		public void test_unNouveauEnvahisseurAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(7,9), 1);
			assertEquals("" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			".......EEE.....\n" + 
			".......EEE.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
		
		@Test
		public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
			
			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
				fail("Dépassement de l'envahisseur à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
				fail("Dépassement de l'envahisseur vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}	
		}
		   
		    @Test
		    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

		       spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(7,9), 3);
		       spaceinvaders.deplacerEnvahisseurVersLaGauche();

		       assertEquals("" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "....EEE........\n" + 
		       "....EEE........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		   }
		    
		   @Test
			public void EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {
				
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(0,9), 3);
				spaceinvaders.deplacerEnvahisseurVersLaGauche();
				
				assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"EEE............\n" + 
				"EEE............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
			}
		   
		   @Test
		    public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaGauche() {

		       spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(1,9), 3);
		       spaceinvaders.deplacerEnvahisseurVersLaGauche();

		       assertEquals("" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "EEE............\n" + 
		       "EEE............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		     }
		   
		   @Test
		    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

		       spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(7,9),3);
		       spaceinvaders.deplacerEnvahisseurVersLaDroite();
		       assertEquals("" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "..........EEE..\n" + 
		       "..........EEE..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		   }
		   
		   @Test
			public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
				
			   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(12,9), 3);
				spaceinvaders.deplacerEnvahisseurVersLaDroite();
				assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"............EEE\n" + 
				"............EEE\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
			}

		   @Test
		    public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaDroite() {

		       spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2),new Position(10,9),3);
		       spaceinvaders.deplacerEnvahisseurVersLaDroite();
		       assertEquals("" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "............EEE\n" + 
		       "............EEE\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		    
}
		   
		   @Test
		   public void test_MissileEnCollisionAvecUnEnvahisseurEtFinDePartie() {
		       
		       spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(4,2),new Position(7,1), 1);
		       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
			   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
			   for (int i = 0; i<4; i++) {
				   spaceinvaders.deplacerMissile();
			   }
			   spaceinvaders.deplacerMissile();
			   spaceinvaders.evoluer(null);
			   
		       assertEquals("" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" + 
		       "...............\n" +
		       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		   }

}