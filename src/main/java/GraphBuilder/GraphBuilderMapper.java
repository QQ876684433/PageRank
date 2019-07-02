package GraphBuilder;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���룺
 * ���� [�ݷ�,0.33333|�ݳ���,0.333333|��ԫ,0.333333]
 * �ݷ� [����,0.25 |�ݳ���,0.25|��ԫ,0.5]
 * �ݳ��� [����,0.33333|�ݷ�,0.333333|��ԫ,0.333333]
 * ��ԫ [����,0.25|�ݷ�,0.5|�ݳ���,0.25]
 */
public class GraphBuilderMapper extends Mapper<Object, Text, Text, Text> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Pattern pattern = Pattern.compile("(\\S+)\\s\\[(.+)\\]");
        Matcher matcher = pattern.matcher(value.toString());
        while (matcher.find()) {
            String a = matcher.group(1);
            String b = matcher.group(2);
            // Ĭ�ϳ�ʼPR��0.5
            context.write(new Text(a),
                    new Text("0.5-" + b));
        }
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\S+)\\s\\[(.+)\\]");
        Matcher matcher = pattern.matcher("���� [�ݷ�,0.33333|�ݳ���,0.333333|��ԫ,0.333333]");
        while (matcher.find()) {
            String a = matcher.group(1);
            String b = matcher.group(2);
            System.out.println(a);
            System.out.println(b);
        }
    }
}
