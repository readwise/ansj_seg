/**
 * 
 */
package org.ansj.solr;

import java.io.Reader;

import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;

/**
 * @author nsun
 *
 */
public class AnsjTokenizerFactory extends TokenizerFactory {

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.util.TokenizerFactory#create(java.io.Reader)
     */
    @Override
    public Tokenizer create(Reader in) {
        Analysis analysis = new ToAnalysis(in);
        Tokenizer tokenizer = new AnsjTokenizer(analysis, in);
        
        return tokenizer;
    }

}
