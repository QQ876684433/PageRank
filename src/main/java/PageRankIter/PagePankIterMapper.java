package PageRankIter;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * ����Ȩ�ظ���ͬ�����ӽڵ����PRֵ
 */
public class PagePankIterMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("-");
        double curRank = Double.parseDouble(splits[0]);
        List<String> linkList = Arrays.asList(splits[1].split("[|]"));
        linkList.forEach(link -> {
            String[] s = link.split(",");
            try {
                context.write(new Text(s[0]), new Text(""+curRank*Double.parseDouble(s[1])));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("���PRֵ����");
            }
        });
        context.write(key, new Text(splits[1]));
    }
}
