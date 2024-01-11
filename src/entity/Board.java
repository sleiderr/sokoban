package entity;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d0296cf5-8c4b-494a-8ca7-c5f4081d4afa")
public class Board {
    @objid ("0694f0b3-8e09-4ac4-930d-bad4b9316eb5")
    public List<Cell> cell = new ArrayList<Cell> ();

    @objid ("88b3d855-cb37-4a0f-bdf4-7b6c9b6ba2dc")
    public class Level:int {
    }

    @objid ("8fcfa69f-6e2e-4806-bc9f-1d9952e06ec7")
    public class nbLignes:int {
    }

    @objid ("fd94ada8-7b08-4cfd-bd96-7b56110413d9")
    public class nbColonnes:int {
    }

}
