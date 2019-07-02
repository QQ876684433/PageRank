package GraphBuilder;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * ���룺
 * ���� [�ݷ�,0.33333|�ݳ���,0.333333|��ԫ,0.333333]
 * �ݷ� [����,0.25 |�ݳ���,0.25|��ԫ,0.5]
 * �ݳ��� [����,0.33333|�ݷ�,0.333333|��ԫ,0.333333]
 * ��ԫ [����,0.25|�ݷ�,0.5|�ݳ���,0.25]
 */
public class GraphBuilderMapper extends Mapper<Text, Text,Text,Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("\t");
        // Ĭ�ϳ�ʼPR��0.5
        context.write(new Text(splits[0]),
                new Text("0.5-"+splits[1].substring(1,splits[1].length()-1)));
    }
}
