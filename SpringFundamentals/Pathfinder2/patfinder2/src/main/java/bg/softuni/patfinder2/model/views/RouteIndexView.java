package bg.softuni.patfinder2.model.views;

public class RouteIndexView {
    private Long id;
    private String name;
    private String description;
    private String thumbNailUrl;

    public RouteIndexView(Long id, String name, String description, String thumbNailUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbNailUrl = thumbNailUrl;
    }

    public RouteIndexView() {
    }

    public Long getId() {
        return id;
    }

    public RouteIndexView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteIndexView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteIndexView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public RouteIndexView setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
        return this;
    }
}
