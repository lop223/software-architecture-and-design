package net.lop223.visitor;

import net.lop223.Playlist;
import net.lop223.PlaylistFolder;
import net.lop223.PlaylistLibraryItem;

public class PlaylistLibraryReportVisitor
        implements PlaylistLibraryVisitor<String> {

    @Override
    public String visit(Playlist playlist) {
        return "Плейлист: " + playlist.getName()
                + " (" + playlist.getTracks().size() + " треків, "
                + playlist.calculateTotalDuration() + ")";
    }

    @Override
    public String visit(PlaylistFolder folder) {
        StringBuilder report = new StringBuilder("Папка: ")
                .append(folder.getName());

        for (PlaylistLibraryItem item : folder.getItems()) {
            report.append(System.lineSeparator())
                    .append("  ")
                    .append(indent(item.accept(this)));
        }

        return report.toString();
    }

    private String indent(String text) {
        return text.replace(
                System.lineSeparator(),
                System.lineSeparator() + "  ");
    }
}