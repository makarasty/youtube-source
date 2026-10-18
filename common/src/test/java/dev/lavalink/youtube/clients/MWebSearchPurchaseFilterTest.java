package dev.lavalink.youtube.clients;

import com.sedmelluq.discord.lavaplayer.tools.JsonBrowser;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.lavalink.youtube.YoutubeAudioSourceManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class MWebSearchPurchaseFilterTest {
    // Trimmed from an actual MWEB search response (ytsearch:어벤져스 엔드게임 영화),
    // containing a purchase-required movie followed by a regular video.
    private static final String SEARCH_RESPONSE = "{\"contents\":{\"sectionListRenderer\":{\"contents\":[{\"itemSectionRenderer\":{\"contents\":[" +
        "{\"videoWithContextRenderer\":{\"headline\":{\"runs\":[{\"text\":\"어벤져스 : 엔드게임 (더빙판)\"}]}," +
        "\"shortBylineText\":{\"runs\":[{\"text\":\"SF • 2019\"}]}," +
        "\"lengthText\":{\"runs\":[{\"text\":\"3:01:15\"}]}," +
        "\"videoId\":\"VihkgZYwxPo\"," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_YPC\",\"label\":\"미리보기 중\"}}," +
        "{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"12세 이상 관람가\"}}]}}," +
        "{\"videoWithContextRenderer\":{\"headline\":{\"runs\":[{\"text\":\"어벤져스 vs 타노스 최종 전투 장면\"}]}," +
        "\"shortBylineText\":{\"runs\":[{\"text\":\"OneVIEW\"}]}," +
        "\"lengthText\":{\"runs\":[{\"text\":\"10:24\"}]}," +
        "\"videoId\":\"hA6hldpSTpk\"," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"4K\"}}]}}" +
        "]}}]}}}";

    @Test
    public void testPurchaseRequiredItemsAreExcludedFromSearchResults() throws IOException {
        List<AudioTrack> tracks = new MWeb().extractSearchResults(new YoutubeAudioSourceManager(true), JsonBrowser.parse(SEARCH_RESPONSE));

        Assertions.assertEquals(1, tracks.size());
        Assertions.assertEquals("hA6hldpSTpk", tracks.get(0).getIdentifier());
    }
}
