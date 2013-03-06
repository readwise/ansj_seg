/**
 * 
 */
package org.ansj.solr;

import java.io.IOException;
import java.io.Reader;

import org.ansj.domain.Term;
import org.ansj.splitWord.Analysis;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

/**
 * @author nsun
 *
 */
public class AnsjTokenizer extends Tokenizer {
    
    private Analysis ansjAnalysis;
    private CharTermAttribute termAtt;
    private OffsetAttribute offsetAtt;
    private TypeAttribute typeAtt;
    
    public AnsjTokenizer(Analysis ana, Reader in) {
        super(in);
        this.ansjAnalysis = ana;
        
        termAtt = addAttribute(CharTermAttribute.class);
        offsetAtt = addAttribute(OffsetAttribute.class);
        typeAtt = addAttribute(TypeAttribute.class);
    }

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.TokenStream#incrementToken()
     */
    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        Term term = ansjAnalysis.next();
        
        if (term != null) {
            termAtt.append(term.getName());
            offsetAtt.setOffset(term.getOffe(), term.getToValue());
            typeAtt.setType(term.getNatrue().natureStr);
            
            return true;
        } else {
            end();
            return false;
        }
    }

}
