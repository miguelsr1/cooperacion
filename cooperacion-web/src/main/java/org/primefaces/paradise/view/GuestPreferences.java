/**
 *  Copyright 2009-2020 PrimeTek.
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.primefaces.paradise.view;

import java.io.IOException;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

    private String theme = "blue";

    private String layout = "default";

    private String menuMode = "layout-menu-slim";

    private boolean darkMenu = true;

    private String componentTheme = "blue";

    private String inputStyle = "outlined";

    private List<ComponentTheme> componentThemes = new ArrayList();

    private List<LayoutColor> layoutColors = new ArrayList();

    @PostConstruct
    public void init() {
        componentThemes.add(new ComponentTheme("Blue", "blue", "#3984b8"));
        componentThemes.add(new ComponentTheme("Deep Purple", "deeppurple", "#B85CC8"));
        componentThemes.add(new ComponentTheme("Green", "green", "#37a533"));
        componentThemes.add(new ComponentTheme("Lime", "lime", "#BAD638"));
        componentThemes.add(new ComponentTheme("Orange", "orange", "#f6ac2b"));
        componentThemes.add(new ComponentTheme("Purple", "purple", "#7e8dcd"));
        componentThemes.add(new ComponentTheme("Turquoise", "turquoise", "#39b8b6"));

        layoutColors.add(new LayoutColor("Default", "default", "#ffffff"));
        layoutColors.add(new LayoutColor("Turquoise", "turquoise", "#39b8b6"));
        layoutColors.add(new LayoutColor("Blue", "blue", "#3984b8"));
        layoutColors.add(new LayoutColor("Deep Purple", "deeppurple", "#B85CC8"));
        layoutColors.add(new LayoutColor("Green", "green", "#37a533"));
        layoutColors.add(new LayoutColor("Lime", "lime", "#BAD638"));
        layoutColors.add(new LayoutColor("Orange", "orange", "#f6ac2b"));
        layoutColors.add(new LayoutColor("Purple", "purple", "#7e8dcd"));
        layoutColors.add(new LayoutColor("Red", "red", "#f28a8b"));

        layoutColors.add(new LayoutColor("Bliss", "bliss", "#360033"));
        layoutColors.add(new LayoutColor("Cheer", "cheer", "#556270"));
        layoutColors.add(new LayoutColor("Crimson", "crimson", "#642b75"));
        layoutColors.add(new LayoutColor("Deep Sea", "deepsea", "#2c3e50"));
        layoutColors.add(new LayoutColor("Disco", "disco", "#4ecdc4"));
        layoutColors.add(new LayoutColor("Horizon", "horizon", "#003973"));
        layoutColors.add(new LayoutColor("Opa", "opa", "#3d7eaa"));
        layoutColors.add(new LayoutColor("Sunset", "sunset", "#e96443"));
    }

    public String getTheme() {
        return theme;
    }

    public String getLayout() {
        return layout;
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getMenuMode() {
        return this.menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public boolean isDarkMenu() {
        return this.darkMenu;
    }

    public void setDarkMenu(boolean value) {
        this.darkMenu = value;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public String getInputStyleClass() {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
    }

    public List<ComponentTheme> getComponentThemes() {
        return componentThemes;
    }

    public class ComponentTheme {

        String name;
        String file;
        String color;

        public ComponentTheme(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public List<LayoutColor> getLayoutColors() {
        return layoutColors;
    }

    public class LayoutColor {

        String name;
        String file;
        String color;

        public LayoutColor(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public void logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().clear();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect(((ServletContext) externalContext.getContext()).getContextPath() + "/index.mined");
            System.gc();
        } catch (IOException ex) {
            Logger.getLogger(GuestPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
