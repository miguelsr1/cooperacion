package sv.gob.mined.cooperacion.model.dto;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@SqlResultSetMapping(name = "defaultAtributoValorDto",
        entities = @EntityResult(entityClass = AtributoValorDto.class))
public class AtributoValorDto implements java.io.Serializable {

    @Id
    private String atributo;
    private String valor;

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
