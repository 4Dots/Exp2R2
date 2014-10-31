/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import bos.Bono;
import bos.Tienda;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class ServicioPersistenciaNoSql extends MongoConfig implements IServicioPersistenciaLocal, Serializable
{

    @Override
    public void create(Object obj) throws Exception
    {
        DBCollection coll = null;
        BasicDBObject doc = null;
        if (obj instanceof Bono)
        {
            Bono bono = (Bono) obj;
            coll = db.getCollection("Bonos");
            doc = new BasicDBObject();
            doc.append("codigo", bono.getCodigo());
            doc.append("tienda", bono.getTienda().getNombre());
            doc.append("valor", bono.getValor());
            doc.append("IDcomprador", bono.getIDcomprador());
            doc.append("nombreComprador", bono.getNombreComprador());
            doc.append("reclamado", bono.isReclamado());
        }
        coll.insert(doc);
    }

    @Override
    public void update(Object obj)
    {
        DBCollection coll = null;
        BasicDBObject doc = null;
        
        if (obj instanceof Bono)
        {
            Bono bono = (Bono) obj;
            coll = db.getCollection("Bonos");
            doc = new BasicDBObject();
            doc.append("codigo", bono.getCodigo());
            doc.append("tienda", bono.getTienda().getNombre());
            doc.append("valor", bono.getValor());
            doc.append("IDcomprador", bono.getIDcomprador());
            doc.append("nombreComprador", bono.getNombreComprador());
            doc.append("reclamado", bono.isReclamado());
        }
        
        coll.save(doc);
        
    }

    @Override
    public void delete(Object obj) throws Exception
    {
        DBCollection coll = null;
        BasicDBObject doc = null;
        
        if (obj instanceof Bono)
        {
            Bono bono = (Bono) obj;
            coll = db.getCollection("Bonos");
            doc = new BasicDBObject();
            doc.append("codigo", bono.getCodigo());
            doc.append("tienda", bono.getTienda().getNombre());
            doc.append("valor", bono.getValor());
            doc.append("IDcomprador", bono.getIDcomprador());
            doc.append("nombreComprador", bono.getNombreComprador());
            doc.append("reclamado", bono.isReclamado());
        }
        
        coll.dropIndex(doc);
    }

    @Override
    public List findAll(Class c)
    {
        if (c.equals(Bono.class))
        {
            List listaBonos = new ArrayList();
            DBCollection coll = db.getCollection("Bonos");
            BasicDBObject query = new BasicDBObject();
            DBCursor cursor = coll.find(query);
            while (cursor.hasNext())
            {
                DBObject dBObject = cursor.next();
                Bono bono = new Bono();
                bono.setCodigo((byte[]) dBObject.get("codigo"));
                bono.setTienda(new Tienda((String) dBObject.get("tienda")));
                bono.setValor((Double) dBObject.get("valor"));
                bono.setIDcomprador((String) dBObject.get("IDcomprador"));
                bono.setNombreComprador((String) dBObject.get("nombreComprador"));
                bono.setReclamado((Boolean) dBObject.get("reclamado"));
                listaBonos.add(bono);
            }
            cursor.close();
            return listaBonos;
        }
        return null;

    }

    @Override
    public Object findById(Class c, Object id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
